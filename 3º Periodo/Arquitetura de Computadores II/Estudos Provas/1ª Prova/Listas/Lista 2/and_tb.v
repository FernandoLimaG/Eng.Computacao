`timescale 1ns/1ns

module and_gate_tb;
    reg a;
    reg b;
    wire y;

    // Instancia o circuito que será testado (DUT)
    and_gate dut (
        .a(a),
        .b(b),
        .y(y)
    );

    initial begin
        // Configura o Icarus para salvar as ondas em um arquivo
        $dumpfile("and_gate_sim.vcd");
        $dumpvars(0, and_gate_tb);

        // Testando todas as combinações da tabela verdade
        a = 0; b = 0; #10;
        a = 0; b = 1; #10;
        a = 1; b = 0; #10;
        a = 1; b = 1; #10;

        $finish; // Encerra a simulação
    end

    // Monitora as mudanças exibindo texto no próprio terminal
    initial begin
        $monitor("Tempo=%0d ns | Entrada A=%b B=%b | Saída Y=%b", $time, a, b, y);
    end
endmodule

