public class control_unit {
    int opcode;
    int funct3;
    boolean funct7_5;
    boolean alu_src;
    boolean mem_to_reg;
    boolean reg_write;
    boolean mem_read;
    boolean mem_write;
    boolean branch;
    int alu_control;

    void update() {
        // Padrões Default
        alu_src = false;
        mem_to_reg = false;
        reg_write = false;
        mem_read = false;
        mem_write = false;
        branch = false;
        alu_control = 0b0000;
        switch (opcode) {
            case 0b0110011: { // Tipo-R (ADD, SUB, AND, OR)
                reg_write = true;
                if (funct3 == 0b000)
                    alu_control = (funct7_5) ? 0b0001 : 0b0000; // SUB ou ADD
                else if (funct3 == 0b111)
                    alu_control = 0b0010; // AND
                else if (funct3 == 0b110)
                    alu_control = 0b0011; // OR
                break;
            }
            case 0b0000011: { // LW (Tipo-I)
                alu_src = true;
                mem_to_reg = true;
                reg_write = true;
                mem_read = true;
                alu_control = 0b0000; // ADD para cálculo de endereço
                break;
            }
            case 0b0100011: { // SW (Tipo-S)
                alu_src = true;
                mem_write = true;
                alu_control = 0b0000; // ADD para cálculo de endereço
                break;
            }
            case 0b1100011: { // BEQ (Tipo-B)
                branch = true;
                alu_control = 0b0001; // SUB para comparação de igualdade
                break;
            }
            default:
                break;
        }
    }
}
