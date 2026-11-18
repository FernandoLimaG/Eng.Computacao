`timescale 1ns/1ps

module tb_register_file;

    reg clk;
    reg reg_write;
    reg [4:0] rs1;
    reg [4:0] rs2;
    reg [4:0] rd;
    reg [31:0] write_data;

    wire [31:0] read_data1;
    wire [31:0] read_data2;

    register_file dut (
        .clk(clk),
        .reg_write(reg_write),
        .rs1(rs1),
        .rs2(rs2),
        .rd(rd),
        .write_data(write_data),
        .read_data1(read_data1),
        .read_data2(read_data2)
    );

    initial clk = 0;
    always #5 clk = ~clk;

    initial begin
        $dumpfile("tb_register_file.vcd");
        $dumpvars(0, tb_register_file);

        $monitor("T=%0t | WE=%b | RD=%d | WD=%d | RS1=%d | R1=%d | RS2=%d | R2=%d",
                 $time, reg_write, rd, write_data,
                 rs1, read_data1, rs2, read_data2);

        reg_write = 0;
        rs1 = 0;
        rs2 = 0;
        rd = 0;
        write_data = 0;

        // Escrita de 10 em x1
        #2;
        reg_write = 1;
        rd = 5'd1;
        write_data = 32'd10;
        #10;

        // Escrita de 20 em x2
        rd = 5'd2;
        write_data = 32'd20;
        #10;

        // Leitura de x1 e x2
        reg_write = 0;
        rs1 = 5'd1;
        rs2 = 5'd2;
        #10;

        // Tentativa de escrita em x0
        reg_write = 1;
        rd = 5'd0;
        write_data = 32'd99;
        #10;

        // Verificacao de x0
        reg_write = 0;
        rs1 = 5'd0;
        rs2 = 5'd1;
        #10;

        // Escrita desabilitada
        rd = 5'd1;
        write_data = 32'd50;
        #10;

        // Conferencia dos valores anteriores
        rs1 = 5'd1;
        rs2 = 5'd2;
        #10;

        $finish;
    end

endmodule
