package ec.edu.espe.arquitectura.banquito.loan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.arquitectura.banquito.loan.dto.LoanRQ;
import ec.edu.espe.arquitectura.banquito.loan.dto.LoanRS;
import ec.edu.espe.arquitectura.banquito.loan.model.Loan;
import ec.edu.espe.arquitectura.banquito.loan.service.LoanService;

@RestController
@RequestMapping("/api/v2/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {

        this.loanService = loanService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<LoanRS> obtainByUuid(
            @PathVariable(name = "uuid") String uuid) {
        try {
            LoanRS loan = this.loanService.listById(uuid);
            return ResponseEntity.ok(loan);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanRQ loan) {
        try {
            Loan loanRS = this.loanService.createLoan(loan);
            return ResponseEntity.ok(loanRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();

        }
    }
}
