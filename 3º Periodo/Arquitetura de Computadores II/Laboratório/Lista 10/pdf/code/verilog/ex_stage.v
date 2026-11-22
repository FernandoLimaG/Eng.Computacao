// ex_stage.v
module ex_stage (
    input  wire [31:0] pc,
    input  wire [31:0] read_data1,
    input  wire [31:0] read_data2,
    input  wire [31:0] imm,
    input  wire        alu_src,
    input  wire [3:0]  alu_control,
    output wire [31:0] alu_result,
    output wire        zero,
    output wire [31:0] pc_target
);
    wire [31:0] alu_operand2;

    // 2ª entrada Mux ALU: Registrador ou Imediato
    assign alu_operand2 = (alu_src) ? imm : read_data2;
    
    // Cálculo do PC de destino para desvios
    assign pc_target = pc + imm;

    // Unidade Lógica e Aritmética (ALU)
    reg [31:0] alu_out;
    assign alu_result = alu_out;
    assign zero       = (alu_out == 32'b0);

    always @(*) begin
        case (alu_control)
            4'b0000: alu_out = read_data1 + alu_operand2; // ADD
            4'b0001: alu_out = read_data1 - alu_operand2; // SUB
            4'b0010: alu_out = read_data1 & alu_operand2; // AND
            4'b0011: alu_out = read_data1 | alu_operand2; // OR
            default: alu_out = 32'b0;
        endcase
    end
endmodule
