// wb_stage.v
module wb_stage (
    input  wire        mem_to_reg,
    input  wire [31:0] alu_result,
    input  wire [31:0] read_data,
    output wire [31:0] write_data_wb
);
    // Mux de seleção final
    assign write_data_wb = (mem_to_reg) ? read_data : alu_result;
endmodule
