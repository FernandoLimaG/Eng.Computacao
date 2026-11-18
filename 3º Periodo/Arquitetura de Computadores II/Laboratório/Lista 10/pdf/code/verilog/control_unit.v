// control_unit.v
module control_unit (
    input  wire [6:0] opcode,
    input  wire [2:0] funct3,
    input  wire       funct7_5,
    output reg        alu_src,
    output reg        mem_to_reg,
    output reg        reg_write,
    output reg        mem_read,
    output reg        mem_write,
    output reg        branch,
    output reg  [3:0] alu_control
);
    always @(*) begin
        // Padrões Default
        alu_src     = 1'b0;
        mem_to_reg  = 1'b0;
        reg_write   = 1'b0;
        mem_read    = 1'b0;
        mem_write   = 1'b0;
        branch      = 1'b0;
        alu_control = 4'b0000;

        case (opcode)
            7'b0110011: begin // Tipo-R (ADD, SUB, AND, OR)
                reg_write = 1'b01;
                if (funct3 == 3'b000)
                    alu_control = (funct7_5) ? 4'b0001 : 4'b0000; // SUB ou ADD
                else if (funct3 == 3'b111)
                    alu_control = 4'b0010; // AND
                else if (funct3 == 3'b110)
                    alu_control = 4'b0011; // OR
            end
            
            7'b0000011: begin // LW (Tipo-I)
                alu_src    = 1'b1;
                mem_to_reg = 1'b1;
                reg_write  = 1'b1;
                mem_read   = 1'b1;
                alu_control = 4'b0000; // ADD para cálculo de endereço
            end

            7'b0100011: begin // SW (Tipo-S)
                alu_src     = 1'b1;
                mem_write   = 1'b1;
                alu_control = 4'b0000; // ADD para cálculo de endereço
            end

            7'b1100011: begin // BEQ (Tipo-B)
                branch      = 1'b1;
                alu_control = 4'b0001; // SUB para comparação de igualdade
            end
            default: ;
        endcase
    end
endmodule
