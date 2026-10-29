// if_stage.v - Módulo de Busca
module if_stage (
    input  wire        clk,
    input  wire        reset,
    input  wire        pc_src,       // Seletor do próximo PC (0 = PC+4, 1 = PC_target)
    input  wire [31:0] pc_target,    // Destino do desvio (Branch)
    output reg  [31:0] pc,
    output wire [31:0] instruction
);
    wire [31:0] pc_next;
    wire [31:0] pc_plus_4;

    assign pc_plus_4 = pc + 4;
    assign pc_next   = (pc_src) ? pc_target : pc_plus_4;

    always @(posedge clk or posedge reset) begin
        if (reset)
            pc <= 32'h00000000;
        else
            pc <= pc_next;
    end

    // Instanciação da Memória de Instruções interna ao IF
    instruction_memory imem (
        .address(pc),
        .instruction(instruction)
    );
endmodule
