public class ex_stage {
    int pc;
    int read_data1;
    int read_data2;
    int imm;
    boolean alu_src;
    int alu_control;
    int alu_result;
    boolean zero;
    int pc_target;
    int alu_operand2;
    int alu_out;

    void update() {
        // Mux para selecionar Registrador ou Imediato
        alu_operand2 = (alu_src) ? imm : read_data2;

        pc_target = pc + imm; // Cálculo do PC alvo para desvios

        // Unidade Lógica e Aritmética (ALU)
        switch (alu_control) {
            case 0b0000:
                alu_out = read_data1 + alu_operand2; // ADD
                break;
            case 0b0001:
                alu_out = read_data1 - alu_operand2; // SUB
                break;
            case 0b0010:
                alu_out = read_data1 & alu_operand2; // AND
                break;
            case 0b0011:
                alu_out = read_data1 | alu_operand2; // OR
                break;
            default:
                alu_out = 0;
                break;
        }
        alu_result = alu_out;
        zero = (alu_out == 0);
    }
}
