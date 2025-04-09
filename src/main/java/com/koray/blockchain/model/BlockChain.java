package com.koray.blockchain.model;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    private final List<Block> chain;

    public BlockChain() {
        chain = new ArrayList<>();
    }

    public void createGenesisBlock(String data) {
        String genesisPreviousHash = "0".repeat(64);
        Block genesisBlock = new Block(data, genesisPreviousHash, 0);
        chain.add(genesisBlock);
    }

    public void addBlock(String data) {
        if (chain.isEmpty()) {
            throw new IllegalStateException("Genesis-Block wurde noch nicht erstellt.");
        }
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(data, previousBlock.getHash(), chain.size());
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return chain;
    }

    /**
     * Gibt eine String-Darstellung der Blockchain zurück: Jede Zeile enthält "Index Hash".
     */
    public String getHashes() {
        StringBuilder sb = new StringBuilder();
        for (Block block : chain) {
            sb.append(block.getIndex())
                    .append(" ")
                    .append("SHA-256")
                    .append(" ")
                    .append(block.getHash())
                    .append("\n");
        }
        return sb.toString();
    }
}