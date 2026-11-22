// id_stage.v - Módulo do Estágio de Decodificação
module id_stage (
    input  wire        clk,
    input  wire [31:0] instruction,
    input  wire        reg_write_wb, // Vem do estágio WB
    input  wire [4:0]  rd_wb,        // Vem do estágio WB
    input  wire [31:0] write_data_wb,// Vem do estágio WB
    output wire [31:0] read_data1,
    output wire [31:0] read_data2,
    output wire [31:0] imm,
    output wire [4:0]  rd,
    output wire [2:0]  funct3,
    output wire        funct7_5,     // Bit 30 da instrução (sub/sra)
    output wire [6:0]  opcode
);
    assign opcode   = instruction[6:0];
    assign funct3   = instruction[14:12];
    assign funct7_5 = instruction[30];
    assign rd       = instruction[11:7];

    register_file reg_file (
        .clk(clk),
        .reg_write(reg_write_wb),
        .rs1(instruction[19:15]),
        .rs2(instruction[24:20]),
        .rd(rd_wb),
        .write_data(write_data_wb),
        .read_data1(read_data1),
        .read_data2(read_data2)
    );

    imm_gen i_gen (
        .inst(instruction),
        .imm(imm)
    );
endmodule
