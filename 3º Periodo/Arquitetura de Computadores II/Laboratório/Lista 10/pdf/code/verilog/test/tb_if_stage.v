`timescale 1ns/1ps

module tb_if_stage;

    reg clk;
    reg reset;
    reg pc_src;
    reg [31:0] pc_target;

    wire [31:0] pc;
    wire [31:0] instruction;

    if_stage dut (
        .clk(clk),
        .reset(reset),
        .pc_src(pc_src),
        .pc_target(pc_target),
        .pc(pc),
        .instruction(instruction)
    );

    initial clk = 0;
    always #5 clk = ~clk;

    initial begin
        $dumpfile("tb_if_stage.vcd");
        $dumpvars(0, tb_if_stage);

        $monitor("T=%0t | CLK=%b | RESET=%b | PC_SRC=%b | PC=%d | INST=%h",
                 $time, clk, reset, pc_src, pc, instruction);

        reset = 1;
        pc_src = 0;
        pc_target = 0;

        #2;
        reset = 0;

        // PC: 0 -> 4 -> 8
        #20;

        // Desvio para o endereco 16
        pc_src = 1;
        pc_target = 32'd16;
        #10;

        // Volta ao incremento sequencial
        pc_src = 0;
        #10;

        // Reset assincrono
        reset = 1;
        #2;
        reset = 0;
        #10;

        $finish;
    end

endmodule
