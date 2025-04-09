package com.koray.blockchain.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Block {
    private final String hash;
    private final String data;
    private final String previousHash;
    private final int index;

    public Block(String data, String previousHash, int index) {
        this.data = data;
        this.previousHash = previousHash;
        this.index = index;
        this.hash = calculateHash(data, previousHash);
    }

    private static String calculateHash(String data, String previousHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = data + previousHash;
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("#### Fehler im SHA-256 Algorithmus bei der Hash-Berechnung ####");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b: bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String getHash() {
        return hash;
    }

    public String getData() {
        return data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getIndex() {
        return index;
    }
}
