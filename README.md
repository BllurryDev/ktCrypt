**ktCrypt** provides custom Kotlin data types that encrypt their underlying `Int` and `Float` values in memory. This protects sensitive data from being easily discovered or modified by memory editing tools like Game Guardian (GG) or similar runtime manipulation tools.

---

## Features

- **AES/GCM encryption** of primitive values (`Int`, `Float`) in memory
- Transparent property delegation in Kotlin for easy usage
- Uses Android's **KeyStore** to securely generate and store encryption keys, preventing key leakage
- Protects runtime values from memory scanning and tampering
- Lightweight and designed for performance-sensitive environments

---

## Motivation

Memory hacking tools scan process memory for known values (e.g., health, score) and allow modification. Plain values stored in RAM can be easily found and changed.

By encrypting these values in memory, ktCrypt makes it significantly harder for such tools to detect or modify critical data, enhancing app/game security.

---

## How It Works

- Values are encrypted using AES in GCM mode (`AES/GCM/NoPadding`).
- Encryption keys are securely generated and stored in Android’s KeyStore system.
- Each encrypted value includes a unique initialization vector (IV) to ensure strong cryptographic security.
- Kotlin’s delegated properties (`getValue` and `setValue`) transparently encrypt and decrypt values when accessed or updated.

---

## Usage Example

```kotlin
// Declare encrypted integer and float properties
var secureHealth by ktCryptInt(100)
var secureSpeed by ktCryptFloat(5.5f)

// Access decrypted values transparently
println("Health: $secureHealth")   // prints 100
println("Speed: $secureSpeed")     // prints 5.5

// Update values securely
secureHealth = 120
secureSpeed = 7.2f
```

## Watch Demo

[![Watch the demo](https://imgur.com/IL0Pa2k)
