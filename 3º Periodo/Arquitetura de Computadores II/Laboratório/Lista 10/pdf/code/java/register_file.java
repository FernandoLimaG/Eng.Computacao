public class register_file {
    boolean clk;
    boolean reg_write;
    int rs1;
    int rs2;
    int rd;
    int write_data;
    int read_data1;
    int read_data2;
    int[] regs = new int[32];

    // Leitura Assíncrona (Combinações livres)
    // Reg x0 é rigidamente aterrado em zero de acordo com a especificação RISC-V
    void update() {
        read_data1 = (rs1 == 0) ? 0 : regs[rs1];
        read_data2 = (rs2 == 0) ? 0 : regs[rs2];
    }

    // Escrita Síncrona
    void posedge_clk() {
        if (reg_write && (rd != 0)) {
            regs[rd] = write_data;
        }
        update();
    }
}
