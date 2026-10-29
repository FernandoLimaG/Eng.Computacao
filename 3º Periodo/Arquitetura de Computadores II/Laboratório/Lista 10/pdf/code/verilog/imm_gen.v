// imm_gen.v - Gerador de Imediatos de acordo com os formatos RISC-V
module imm_gen (
    input  wire [31:0] inst,
    output reg  [31:0] imm
);
    always @() begin
        case (inst[6:2])
            5'b00000: imm = {{20{inst[31]}}, inst[31:20]};             // Tipo-I (LW)
            5'b01000: imm = {{20{inst[31]}}, inst[31:25], inst[11:7]}; // Tipo-S (SW)
            5'b11000: imm = {{20{inst[31]}}, inst[7], inst[30:25], inst[11:8], 1'b0}; // Tipo-B (BEQ)
            default:  imm = 32'b0;
        endcase
    end
endmodule
