public class if_stage {
    boolean clk;
    boolean reset;
    boolean pc_src;
    int pc_target;
    int pc;
    int instruction;
    int pc_next;
    int pc_plus_4;

    instruction_memory imem = new instruction_memory();

    void update() {
        pc_plus_4 = pc + 4;
        pc_next = (pc_src) ? pc_target : pc_plus_4;
        update_imem();
    }

    void posedge_clk() {
        update();
        if (reset)
            pc = 0x00000000;
        else
            pc = pc_next;
        update();
    }

    void posedge_reset() {
        pc = 0x00000000;
        update();
    }

    // Instanciação da Memória de Instruções interna ao IF
    void update_imem() {
        imem.address = pc;
        imem.update();
        instruction = imem.instruction;
    }
}
