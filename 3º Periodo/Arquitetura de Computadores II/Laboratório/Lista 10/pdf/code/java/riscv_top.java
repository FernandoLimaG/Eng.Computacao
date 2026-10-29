public class riscv_top {
    boolean clk;
    boolean reset;

    // Fios de interconexão entre os estágios
    int pc, instruction;
    int read_data1, read_data2, imm;
    int rd;
    int funct3;
    boolean funct7_5;
    int opcode;

    // Sinais de Controle
    boolean alu_src, mem_to_reg, reg_write, mem_read, mem_write, branch;
    int alu_control;

    // Sinais de Execução e Memória
    int alu_result, pc_target, read_data, write_data_wb;
    boolean zero;
    boolean pc_src;

    // Estágio 1: Instruction Fetch
    if_stage stage_if = new if_stage();

    // Estágio 2: Instruction Decode
    id_stage stage_id = new id_stage();

    // Unidade de Controle Central
    control_unit control = new control_unit();

    // Estágio 3: Execution
    ex_stage stage_ex = new ex_stage();

    // Estágio 4: Memory Unit
    mem_stage stage_mem = new mem_stage();

    // Estágio 5: Write Back
    wb_stage stage_wb = new wb_stage();

    void update() {
        // Estágio 1: Instruction Fetch
        stage_if.clk = clk;
        stage_if.reset = reset;
        stage_if.pc_src = pc_src;
        stage_if.pc_target = pc_target;
        stage_if.update();
        pc = stage_if.pc;
        instruction = stage_if.instruction;

        // Estágio 2: Instruction Decode
        stage_id.clk = clk;
        stage_id.instruction = instruction;
        stage_id.reg_write_wb = reg_write;
        stage_id.rd_wb = rd;
        stage_id.write_data_wb = write_data_wb;
        stage_id.update();
        read_data1 = stage_id.read_data1;
        read_data2 = stage_id.read_data2;
        imm = stage_id.imm;
        rd = stage_id.rd;
        funct3 = stage_id.funct3;
        funct7_5 = stage_id.funct7_5;
        opcode = stage_id.opcode;

        // Unidade de Controle Central
        control.opcode = opcode;
        control.funct3 = funct3;
        control.funct7_5 = funct7_5;
        control.update();
        alu_src = control.alu_src;
        mem_to_reg = control.mem_to_reg;
        reg_write = control.reg_write;
        mem_read = control.mem_read;
        mem_write = control.mem_write;
        branch = control.branch;
        alu_control = control.alu_control;

        // Estágio 3: Execution
        stage_ex.pc = pc;
        stage_ex.read_data1 = read_data1;
        stage_ex.read_data2 = read_data2;
        stage_ex.imm = imm;
        stage_ex.alu_src = alu_src;
        stage_ex.alu_control = alu_control;
        stage_ex.update();
        alu_result = stage_ex.alu_result;
        zero = stage_ex.zero;
        pc_target = stage_ex.pc_target;

        // Estágio 4: Memory Unit
        stage_mem.clk = clk;
        stage_mem.mem_read = mem_read;
        stage_mem.mem_write = mem_write;
        stage_mem.alu_result = alu_result;
        stage_mem.write_data = read_data2; // Dado salvo = RS2
        stage_mem.update();
        read_data = stage_mem.read_data;

        // Estágio 5: Write Back
        stage_wb.mem_to_reg = mem_to_reg;
        stage_wb.alu_result = alu_result;
        stage_wb.read_data = read_data;
        stage_wb.update();
        write_data_wb = stage_wb.write_data_wb;

        pc_src = branch && zero; // Branch AND zero

        stage_id.reg_write_wb = reg_write;
        stage_id.rd_wb = rd;
        stage_id.write_data_wb = write_data_wb;
        stage_if.pc_src = pc_src;
        stage_if.pc_target = pc_target;
    }

    void posedge_clk() {
        update();
        stage_mem.posedge_clk();
        stage_id.posedge_clk();
        stage_if.posedge_clk();
        update();
    }

    void posedge_reset() {
        stage_if.reset = reset;
        stage_if.posedge_reset();
        update();
    }
}
