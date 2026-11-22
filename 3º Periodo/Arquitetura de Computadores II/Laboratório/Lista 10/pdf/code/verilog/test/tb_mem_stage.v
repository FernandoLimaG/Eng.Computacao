`timescale 1ns/1ps

module tb_mem_stage;

    reg clk;
    reg mem_read;
    reg mem_write;
    reg [31:0] alu_result;
    reg [31:0] write_data;

    wire [31:0] read_data;

    mem_stage dut (
        .clk(clk),
        .mem_read(mem_read),
        .mem_write(mem_write),
        .alu_result(alu_result),
        .write_data(write_data),
        .read_data(read_data)
    );

    initial clk = 0;
    always #5 clk = ~clk;

    initial begin
        $dumpfile("tb_mem_stage.vcd");
        $dumpvars(0, tb_mem_stage);

        $monitor("T=%0t | Read=%b | Write=%b | Address=%d | WD=%d | RD=%d",
                 $time, mem_read, mem_write,
                 alu_result, write_data, read_data);

        mem_read = 0;
        mem_write = 0;
        alu_result = 0;
        write_data = 0;

        // Escrever 100 no endereco 0
        #2;
        mem_write = 1;
        alu_result = 32'd0;
        write_data = 32'd100;
        #10;

        // Escrever 200 no endereco 4
        alu_result = 32'd4;
        write_data = 32'd200;
        #10;

        // Habilitar leitura e desabilitar escrita
        mem_write = 0;
        mem_read = 1;

        // Ler endereco 0
        alu_result = 32'd0;
        #10;

        // Ler endereco 4
        alu_result = 32'd4;
        #10;

        // Desabilitar leitura
        mem_read = 0;
        #10;

        // Tentativa de escrita desabilitada
        alu_result = 32'd0;
        write_data = 32'd999;
        #10;

        // Verificar se o valor anterior foi mantido
        mem_read = 1;
        #10;

        $finish;
    end

endmodule
