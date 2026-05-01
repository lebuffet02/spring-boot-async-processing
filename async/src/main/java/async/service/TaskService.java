package async.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import static java.lang.System.out;

@Service
public class TaskService {

    private final Executor executor;

    public TaskService(@Qualifier("customExecutor") Executor executor) {
        this.executor = executor;
    }

    public String semParalelismo() {
        long inicio = System.currentTimeMillis();
        String r1 = tarefa("Tarefa 1");
        String r2 = tarefa("Tarefa 2");
        String r3 = tarefa("Tarefa 3");
        long fim = System.currentTimeMillis();
        out.println(r1 + " \n" + r2 + " \n" + r3 + " \nTempo total: " + (fim - inicio) + " ms");
        return String.format("%s%n%s%n%s%n Tempo total: %d ms", r1, r2, r3, (fim - inicio));
    }

    public String comParalelismo() {
        long inicio = System.currentTimeMillis();
        CompletableFuture<String> c1 = executar("Tarefa 1");
        CompletableFuture<String> c2 = executar("Tarefa 2");
        CompletableFuture<String> c3 = executar("Tarefa 3");
        CompletableFuture.allOf(c1, c2, c3).join();
        long fim = System.currentTimeMillis();
        out.println(c1.join() + " \n" + c2.join() + " \n" + c3.join() + " \nTempo total: " + (fim - inicio) + " ms");
        return String.format("%s%n%s%n%s%n Tempo total: %d ms", c1.join(), c2.join(), c3.join(), (fim - inicio));
    }

    public String tarefa(String nome) {
        try {
            Thread.sleep(3000);
            return nome + " executada na thread: " + Thread.currentThread().getName();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private CompletableFuture<String> executar(String nome) {
        return CompletableFuture
                .supplyAsync(() -> tarefa(nome), executor)
                .orTimeout(3, TimeUnit.SECONDS)
                .exceptionally(ex -> "Erro em " + nome + ": " + ex.getMessage());
    }
}