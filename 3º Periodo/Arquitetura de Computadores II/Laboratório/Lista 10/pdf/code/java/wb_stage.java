public class wb_stage {
    boolean mem_to_reg;
    int alu_result;
    int read_data;
    int write_data_wb;

    // Mux de seleção final
    void update() {
        write_data_wb = (mem_to_reg) ? read_data : alu_result;
    }
}
