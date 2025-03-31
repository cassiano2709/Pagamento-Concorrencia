package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

@Service
public class PaymentService {

    private static final Logger logger = Logger.getLogger(PaymentService.class.getName());

    private final PaymentRepository repository;
    private final Executor taskExecutor;

    public PaymentService(PaymentRepository repository, Executor taskExecutor) {
        this.repository = repository;
        this.taskExecutor = taskExecutor;
    }

    // 🟢 Novo método para criar pagamento
    public Payment createPayment(Payment payment) {
        return repository.save(payment);
    }

    // 🟢 Novo método para buscar pagamento por ID
    public Optional<Payment> getPaymentById(Long id) {
        return repository.findById(id);
    }

    public void processPayment(Long paymentId, BigDecimal deduction) {
        int maxRetries = 2;
        int attempts = 0;
        boolean success = false;

        while (!success && attempts < maxRetries) {
            try {
                attempts++;
                logger.info("[Thread " + Thread.currentThread().getName() + "] Attempt " + attempts + " to deduct R$" + deduction);

                Payment payment = repository.findById(paymentId).orElseThrow();
                payment.setAmount(payment.getAmount().subtract(deduction));
                repository.save(payment);

                logger.info("[Thread " + Thread.currentThread().getName() + "] Success: New amount is R$" + payment.getAmount());
                success = true;
            } catch (ObjectOptimisticLockingFailureException e) {
                logger.warning("[Thread " + Thread.currentThread().getName() + "] Optimistic lock failure, retrying...");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {}
            } catch (Exception e) {
                logger.severe("[Thread " + Thread.currentThread().getName() + "] Error processing payment: " + e.getMessage());
                break;
            }
        }

        if (!success) {
            logger.severe("[Thread " + Thread.currentThread().getName() + "] Failed to process payment after " + maxRetries + " attempts.");
        }
    }

    public void simulateConcurrentPayments(Long id, BigDecimal amount) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> processPayment(id, amount), taskExecutor);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        logger.info("All concurrent payments processed.");
    }
}
