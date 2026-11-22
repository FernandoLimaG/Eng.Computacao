// mem_stage.v
module mem_stage (
    input  wire        clk,
    input  wire        mem_read,
    input  wire        mem_write,
    input  wire [31:0] alu_result,
    input  wire [31:0] write_data,
    output wire [31:0] read_data
);
    reg [31:0] dmem [0:255]; // Memória de dados de 256 palavras

    // Escrita Síncrona
    always @(posedge clk) begin
        if (mem_write) begin
            dmem[alu_result[9:2]] <= write_data;
        end
    end

    // Leitura Combinatória/Assíncrona
    assign read_data = (mem_read) ? dmem[alu_result[9:2]] : 32'b0;
endmodule
