`timescale 1ns/1ps

module tb_id_stage;

    reg clk;
    reg [31:0] instruction;
    reg reg_write_wb;
    reg [4:0] rd_wb;
    reg [31:0] write_data_wb;

    wire [31:0] read_data1;
    wire [31:0] read_data2;
    wire [31:0] imm;
    wire [4:0] rd;
    wire [2:0] funct3;
    wire funct7_5;
    wire [6:0] opcode;

    id_stage dut (
        .clk(clk),
        .instruction(instruction),
        .reg_write_wb(reg_write_wb),
        .rd_wb(rd_wb),
        .write_data_wb(write_data_wb),
        .read_data1(read_data1),
        .read_data2(read_data2),
        .imm(imm),
        .rd(rd),
        .funct3(funct3),
        .funct7_5(funct7_5),
        .opcode(opcode)
    );

    initial clk = 0;
    always #5 clk = ~clk;

    initial begin
        $dumpfile("tb_id_stage.vcd");
        $dumpvars(0, tb_id_stage);
        $monitor("T=%0t | INST=%h | OPCODE=%b | RD=%d | F3=%b | F7=%b | R1=%d | R2=%d | IMM=%h",
            $time, instruction, opcode, rd, funct3, funct7_5, read_data1, read_data2, imm);

        instruction = 32'h002081B3;
        reg_write_wb = 0;
        rd_wb = 0;
        write_data_wb = 0;

        // Inicializar x1 = 10
        #2;
        reg_write_wb = 1;
        rd_wb = 5'd1;
        write_data_wb = 32'd10;
        #10;

        // Inicializar x2 = 20
        rd_wb = 5'd2;
        write_data_wb = 32'd20;
        #10;

        // Inicializar x3 = 30
        rd_wb = 5'd3;
        write_data_wb = 32'd30;
        #10;

        // Inicializar x4 = 40
        rd_wb = 5'd4;
        write_data_wb = 32'd40;
        #10;

        reg_write_wb = 0;

        // ADD x3, x1, x2
        instruction = 32'h002081B3;
        #10;

        // SUB x3, x1, x2
        instruction = 32'h402081B3;
        #10;

        // LW x4, 4(x3)
        instruction = 32'h0041A203;
        #10;

        // SW x4, 8(x3)
        instruction = 32'h0041A423;
        #10;

        // BEQ x1, x2, +8
        instruction = {1'b0, 6'b000000, 5'd2, 5'd1,
                       3'b000, 4'b0100, 1'b0, 7'b1100011};
        #10;

        $finish;
    end

endmodule
