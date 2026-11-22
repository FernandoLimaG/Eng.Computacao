public class instruction_memory {
    int address;
    int instruction;
    int[] rom = new int[256];

    public instruction_memory() {
        // Exemplo: add x3, x1, x2 -> 002081B3
        rom[0] = 0x002081B3;
        // Exemplo: lw x4, 4(x3) -> 0041a203
        rom[1] = 0x0041a203;
        // Exemplo: sw x4, 8(x3) -> 0041a423
        rom[2] = 0x0041a423;
    }

    // Alinhamento por palavra (Word-aligned)
    void update() {
        instruction = rom[(address >>> 2) & 0xFF];
    }
}
