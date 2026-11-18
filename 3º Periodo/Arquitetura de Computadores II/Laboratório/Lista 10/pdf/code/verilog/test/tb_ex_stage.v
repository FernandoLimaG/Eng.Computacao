`timescale 1ns/1ps

module tb_ex_stage;

    reg [31:0] pc;
    reg [31:0] read_data1;
    reg [31:0] read_data2;
    reg [31:0] imm;
    reg alu_src;
    reg [3:0] alu_control;

    wire [31:0] alu_result;
    wire zero;
    wire [31:0] pc_target;

    ex_stage dut (
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

    initial begin
        $dumpfile("tb_ex_stage.vcd");
        $dumpvars(0, tb_ex_stage);

        $monitor("T=%0t | PC=%d | A=%d | B=%d | IMM=%d | Src=%b | Ctrl=%b | Result=%d | Zero=%b | Target=%d",
                 $time, pc, read_data1, read_data2, imm,
                 alu_src, alu_control, alu_result, zero, pc_target);

        pc = 32'd0;
        read_data1 = 32'd10;
        read_data2 = 32'd5;
        imm = 32'd8;
        alu_src = 0;

        // ADD: 10 + 5 = 15
        alu_control = 4'b0000;
        #10;

        // SUB: 10 - 5 = 5
        alu_control = 4'b0001;
        #10;

        // AND: 10 & 5 = 0
        alu_control = 4'b0010;
        #10;

        // OR: 10 | 5 = 15
        alu_control = 4'b0011;
        #10;

        // ADD com imediato: 10 + 8 = 18
        alu_src = 1;
        alu_control = 4'b0000;
        #10;

        // SUB com operandos iguais
        // Resultado = 0 e zero = 1
        alu_src = 0;
        read_data2 = 32'd10;
        alu_control = 4'b0001;
        #10;

        // Calculo do destino do desvio
        // PC = 100, IMM = 16, Target = 116
        pc = 32'd100;
        imm = 32'd16;
        #10;

        // Operacao nao reconhecida
        alu_control = 4'b1111;
        #10;

        $finish;
    end

endmodule
