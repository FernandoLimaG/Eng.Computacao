`timescale 1ns/1ps

module tb_riscv_top;

    reg clk;
    reg reset;

    riscv_top dut (
        .clk(clk),
        .reset(reset)
    );

    initial clk = 0;
    always #5 clk = ~clk;

    initial begin
        $dumpfile("tb_riscv_top.vcd");
        $dumpvars(0, tb_riscv_top);

        $display("=== TESTE RISC-V TOP ===");

        $monitor("T=%0t | PC=%d | Inst=%h | ALU=%d | MemRead=%b | MemWrite=%b | WB=%d",
                 $time, dut.pc, dut.instruction,
                 dut.alu_result, dut.mem_read,
                 dut.mem_write, dut.write_data_wb);

        reset = 1;

        // Inicializacao dos registradores
        dut.stage_id.reg_file.regs[1] = 32'd4;
        dut.stage_id.reg_file.regs[2] = 32'd8;

        // Inicializacao da memoria de dados
        dut.stage_mem.dmem[4] = 32'd99;

        #2;
        reset = 0;

        // ADD x3, x1, x2
        #4;
        $display("=== RESULTADO ADD ===");
        $display("x3 = %d", dut.stage_id.reg_file.regs[3]);

        // LW x4, 4(x3)
        #10;
        $display("=== RESULTADO LW ===");
        $display("x4 = %d", dut.stage_id.reg_file.regs[4]);

        // SW x4, 8(x3)
        #10;
        $display("=== RESULTADO SW ===");
        $display("Memoria[20] = %d", dut.stage_mem.dmem[5]);

        // Verificacao final
        if (dut.stage_id.reg_file.regs[3] === 32'd12 &&
            dut.stage_id.reg_file.regs[4] === 32'd99 &&
            dut.stage_mem.dmem[5] === 32'd99)
            $display("TESTE INTEGRADO: PASS");
        else
            $display("TESTE INTEGRADO: FAIL");

        $finish;
    end

endmodule
