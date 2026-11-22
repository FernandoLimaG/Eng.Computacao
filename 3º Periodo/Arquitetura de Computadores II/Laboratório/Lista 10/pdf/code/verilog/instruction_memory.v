// imem.v - Memória de Instruções (Instanciação ROM simples)
module instruction_memory (
    input  wire [31:0] address,
    output wire [31:0] instruction
);
    reg [31:0] rom [0:255]; // 256 palavras de 32 bits

    // Inicialização da memória para testes (exemplo)
    initial begin
        // Exemplo: add x3, x1, x2 -> 002081B3
        rom[0] = 32'h002081B3;
        // Exemplo: lw x4, 4(x3)   -> 0041a203
        rom[1] = 32'h0041a203;
        // Exemplo: sw x4, 8(x3)   -> 0041a423
        rom[2] = 32'h0041a423;
    end

    // Alinhamento por palavra (Word-aligned)
    assign instruction = rom[address[9:2]]; 
endmodule
