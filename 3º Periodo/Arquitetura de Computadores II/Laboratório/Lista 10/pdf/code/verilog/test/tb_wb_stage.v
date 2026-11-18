`timescale 1ns/1ps

module tb_wb_stage;

    reg mem_to_reg;
    reg [31:0] alu_result;
    reg [31:0] read_data;

    wire [31:0] write_data_wb;

    wb_stage dut (
        .mem_to_reg(mem_to_reg),
        .alu_result(alu_result),
        .read_data(read_data),
        .write_data_wb(write_data_wb)
    );

    initial begin
        $dumpfile("tb_wb_stage.vcd");
        $dumpvars(0, tb_wb_stage);

        $monitor("T=%0t | MemToReg=%b | ALU=%d | Mem=%d | WB=%d",
                 $time, mem_to_reg, alu_result,
                 read_data, write_data_wb);

        alu_result = 32'd15;
        read_data = 32'd100;

        // Selecionar resultado da ALU
        mem_to_reg = 0;
        #10;

        // Selecionar dado da memoria
        mem_to_reg = 1;
        #10;

        // Alterar os dois dados
        alu_result = 32'd25;
        read_data = 32'd200;
        #10;

        // Selecionar resultado da ALU novamente
        mem_to_reg = 0;
        #10;

        // Alterar apenas resultado da ALU
        alu_result = 32'd50;
        #10;

        // Selecionar dado da memoria novamente
        mem_to_reg = 1;
        #10;

        $finish;
    end

endmodule
