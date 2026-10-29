public class id_stage {
    boolean clk;
    int instruction;
    boolean reg_write_wb;
    int rd_wb;
    int write_data_wb;
    int read_data1;
    int read_data2;
    int imm;
    int rd;
    int funct3;
    boolean funct7_5;
    int opcode;

    register_file reg_file = new register_file();
    imm_gen i_gen = new imm_gen();

    void update() {
        opcode = instruction & 0x7F;
        funct3 = (instruction >>> 12) & 0x7;
        funct7_5 = ((instruction >>> 30) & 0x1) != 0;
        rd = (instruction >>> 7) & 0x1F;
        update_reg_file();
        update_imm_gen();
    }

    void update_reg_file() {
        reg_file.clk = clk;
        reg_file.reg_write = reg_write_wb;
        reg_file.rs1 = (instruction >>> 15) & 0x1F;
        reg_file.rs2 = (instruction >>> 20) & 0x1F;
        reg_file.rd = rd_wb;
        reg_file.write_data = write_data_wb;
        reg_file.update();
        read_data1 = reg_file.read_data1;
        read_data2 = reg_file.read_data2;
    }

    void update_imm_gen() {
        i_gen.inst = instruction;
        i_gen.update();
        imm = i_gen.imm;
    }

    void posedge_clk() {
        update();
        reg_file.posedge_clk();
        read_data1 = reg_file.read_data1;
        read_data2 = reg_file.read_data2;
    }
}
