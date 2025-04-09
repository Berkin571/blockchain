package com.koray.blockchain.controller;

import com.koray.blockchain.model.BlockChain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    private final BlockChain blockchain;

    public BlockchainController() {
        this.blockchain = new BlockChain();
        // Erstelle den Genesis-Block beim Start des Controllers.
        blockchain.createGenesisBlock("Jamal an Joe -> 150 EUR");
    }

    /**
     * Zeigt die aktuelle Blockchain (Index und Hash-Werte) an.
     * Aufruf: GET http://localhost:8080/api/blockchain
     */
    @GetMapping
    public ResponseEntity<String> getBlockchain() {
        return ResponseEntity.ok(blockchain.getHashes());
    }

    /**
     * Fügt einen neuen Block hinzu.
     * Aufruf: POST http://localhost:8080/api/blockchain/add?data=<DeineDaten>
     */
    @PostMapping("/add")
    public ResponseEntity<String> addBlock(@RequestParam String data) {
        try {
            blockchain.addBlock(data);
            return ResponseEntity.ok(blockchain.getHashes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fehler: " + e.getMessage());
        }
    }
}
