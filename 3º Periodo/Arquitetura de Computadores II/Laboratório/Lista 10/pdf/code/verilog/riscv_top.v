// riscv_top.v
module riscv_top (
    input wire clk,
    input wire reset
);
    // Fios de interconexão entre os estágios
    wire [31:0] pc, instruction;
    wire [31:0] read_data1, read_data2, imm;
    wire [4:0]  rd;
    wire [2:0]  funct3;
    wire        funct7_5;
    wire [6:0]  opcode;
    
    // Sinais de Controle
    wire alu_src, mem_to_reg, reg_write, mem_read, mem_write, branch;
    wire [3:0] alu_control;

    // Sinais de Execução e Memória
    wire [31:0] alu_result, pc_target, read_data, write_data_wb;
    wire zero;
    wire pc_src;

    // Lógica do controle de Desvio (Branch AND Zero)
    assign pc_src = branch & zero;

    // Estágio 1: Instruction Fetch
    if_stage stage_if (
        .clk(clk),
        .reset(reset),
        .pc_src(pc_src),
        .pc_target(pc_target),
        .pc(pc),
        .instruction(instruction)
    );

    // Estágio 2: Instruction Decode
    id_stage stage_id (
        .clk(clk),
        .instruction(instruction),
        .reg_write_wb(reg_write),
        .rd_wb(rd),
        .write_data_wb(write_data_wb),
        .read_data1(read_data1),
        .read_data2(read_data2),
        .imm(imm),
        .rd(rd),
        .funct3(funct3),
        .funct7_5(funct7_5),
        .opcode(opcode)
    );

    // Unidade de Controle Central (Atua no ID, mas distribui para EX, MEM e WB)
    control_unit control (
        .opcode(opcode),
        .funct3(funct3),
        .funct7_5(funct7_5),
        .alu_src(alu_src),
        .mem_to_reg(mem_to_reg),
        .reg_write(reg_write),
        .mem_read(mem_read),
        .mem_write(mem_write),
        .branch(branch),
        .alu_control(alu_control)
    );

    // Estágio 3: Execution
    ex_stage stage_ex (
        .pc(pc),
        .read_data1(read_data1),
        .read_data2(read_data2),
        .imm(imm),
        .alu_src(alu_src),
        .alu_control(alu_control),
        .alu_result(alu_result),
        .zero(zero),
        .pc_target(pc_target)
    );

    // Estágio 4: Memory Unit
    mem_stage stage_mem (
        .clk(clk),
        .mem_read(mem_read),
        .mem_write(mem_write),
        .alu_result(alu_result),
        .write_data(read_data2), // O dado a ser salvo é sempre oriundo de RS2
        .read_data(read_data)
    );

    // Estágio 5: Write Back
    wb_stage stage_wb (
        .mem_to_reg(mem_to_reg),
        .alu_result(alu_result),
        .read_data(read_data),
        .write_data_wb(write_data_wb)
    );

endmodule
