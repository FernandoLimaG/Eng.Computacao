// register_file.v - Banco de Registradores (X0 a X31)
module register_file (
    input  wire        clk,
    input  wire        reg_write,
    input  wire [4:0]  rs1,
    input  wire [4:0]  rs2,
    input  wire [4:0]  rd,
    input  wire [31:0] write_data,
    output wire [31:0] read_data1,
    output wire [31:0] read_data2
);
    reg [31:0] regs [0:31];

    // Leitura Assíncrona (Combinações livres)
    // Reg x0 é rigidamente aterrado em zero de acordo com a especificação RISC-V
    assign read_data1 = (rs1 == 5'b0) ? 32'b0 : regs[rs1];
    assign read_data2 = (rs2 == 5'b0) ? 32'b0 : regs[rs2];

    // Escrita Síncrona
    integer i;
    always @(posedge clk) begin
        if (reg_write && (rd != 5'b0)) begin
            regs[rd] <= write_data;
        end
    end
endmodule
