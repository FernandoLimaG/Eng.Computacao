`timescale 1ns/1ps

module tb_imm_gen;

    reg [31:0] inst;
    wire [31:0] imm;

    imm_gen dut (
        .inst(inst),
        .imm(imm)
    );

    initial begin
        $dumpfile("tb_imm_gen.vcd");
        $dumpvars(0, tb_imm_gen);

        $monitor("T=%0t | INST=%h | IMM=%h | DECIMAL=%0d",
                 $time, inst, imm, $signed(imm));

        // LW com imediato +4
        inst = {12'd4, 5'd3, 3'b010,
                5'd4, 7'b0000011};
        #10;

        // LW com imediato -4
        inst = {12'hFFC, 5'd3, 3'b010,
                5'd4, 7'b0000011};
        #10;

        // SW com imediato +8
        inst = {7'b0000000, 5'd4, 5'd3,
                3'b010, 5'b01000, 7'b0100011};
        #10;

        // SW com imediato -4
        inst = {7'b1111111, 5'd4, 5'd3,
                3'b010, 5'b11100, 7'b0100011};
        #10;

        // BEQ com deslocamento +8
        inst = {1'b0, 6'b000000, 5'd2, 5'd1,
                3'b000, 4'b0100, 1'b0, 7'b1100011};
        #10;

        // BEQ com deslocamento -4
        inst = {1'b1, 6'b111111, 5'd2, 5'd1,
                3'b000, 4'b1110, 1'b1, 7'b1100011};
        #10;

        // ADD: nao utiliza imediato
        inst = 32'h002081B3;
        #10;

        $finish;
    end

endmodule
