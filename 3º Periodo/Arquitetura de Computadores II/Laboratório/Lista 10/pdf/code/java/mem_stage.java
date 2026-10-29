public class mem_stage {
    boolean clk;
    boolean mem_read;
    boolean mem_write;
    int alu_result;
    int write_data;
    int read_data;
    int[] dmem = new int[256]; // Memória de dados de 256 palavras

    // Escrita Síncrona
    void posedge_clk() {
        if (mem_write) {
            dmem[(alu_result >>> 2) & 0xFF] = write_data;
        }
        update();
    }

    // Leitura Combinatória/Assíncrona
    void update() {
        read_data = (mem_read) ? dmem[(alu_result >>> 2) & 0xFF] : 0;
    }
}
