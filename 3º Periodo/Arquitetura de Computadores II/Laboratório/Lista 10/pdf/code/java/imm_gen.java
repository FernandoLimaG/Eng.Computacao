public class imm_gen {
    int inst;
    int imm;

    void update() {
        switch ((inst >>> 2) & 0x1F) {
            case 0b00000:
                imm = inst >> 20; // Tipo-I (LW)
                break;
            case 0b01000:
                imm = ((inst >> 25) << 5) | ((inst >>> 7) & 0x1F); // Tipo-S (SW)
                break;
            case 0b11000:
                imm = ((inst >> 31) << 12) |
                      (((inst >>> 7) & 0x1) << 11) |
                      (((inst >>> 25) & 0x3F) << 5) |
                      (((inst >>> 8) & 0xF) << 1); // Tipo-B (BEQ)
                break;
            default:
                imm = 0;
                break;
        }
    }
}
