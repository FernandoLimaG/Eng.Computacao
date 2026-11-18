`timescale 1ns/1ps

module tb_control_unit;

    reg [6:0] opcode;
    reg [2:0] funct3;
    reg funct7_5;

    wire alu_src;
    wire mem_to_reg;
    wire reg_write;
    wire mem_read;
    wire mem_write;
    wire branch;
    wire [3:0] alu_control;

    control_unit dut (
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

    initial begin
        $dumpfile("tb_control_unit.vcd");
        $dumpvars(0, tb_control_unit);

        $monitor("T=%0t | OP=%b | F3=%b | F7=%b | ALUSrc=%b | MemToReg=%b | RegWrite=%b | MemRead=%b | MemWrite=%b | Branch=%b | ALUCtrl=%b",
                 $time, opcode, funct3, funct7_5,
                 alu_src, mem_to_reg, reg_write,
                 mem_read, mem_write, branch, alu_control);

        // ADD
        opcode = 7'b0110011;
        funct3 = 3'b000;
        funct7_5 = 0;
        #10;

        // SUB
        funct7_5 = 1;
        #10;

        // AND
        funct7_5 = 0;
        funct3 = 3'b111;
        #10;

        // OR
        funct3 = 3'b110;
        #10;

        // LW
        opcode = 7'b0000011;
        funct3 = 3'b010;
        #10;

        // SW
        opcode = 7'b0100011;
        #10;

        // BEQ
        opcode = 7'b1100011;
        funct3 = 3'b000;
        #10;

        // Opcode invalido
        opcode = 7'b1111111;
        #10;

        $finish;
    end

endmodule
