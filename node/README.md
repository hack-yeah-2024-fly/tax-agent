# Tax Agent UI

## Prerequisites

Before you begin, ensure you have the following installed:

1. Node.js: This project requires Node.js version 20.x or higher. You can download it from [nodejs.org](https://nodejs.org/) or use a version manager like nvm.

2. pnpm: This project uses pnpm as the package manager. Install it globally by running:

   ```bash
   npm install -g pnpm@9.1.0
   ```

## Environment Setup

### Windows

1. Install Node.js 20.x from [nodejs.org](https://nodejs.org/).
2. Install pnpm globally using PowerShell:
   ```powershell
   npm install -g pnpm@9.1.0
   ```

### WSL (Windows Subsystem for Linux)

1. Install Node.js 20.x using nvm:
   ```bash
   curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
   nvm install 20
   nvm use 20
   ```
2. Install pnpm:
   ```bash
   npm install -g pnpm@9.1.0
   ```

### Linux

1. Install Node.js 20.x using nvm:
   ```bash
   curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
   nvm install 20
   nvm use 20
   ```
2. Install pnpm:
   ```bash
   npm install -g pnpm@9.1.0
   ```

### macOS

1. Install Node.js 20.x using nvm:
   ```bash
   curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
   nvm install 20
   nvm use 20
   ```
2. Install pnpm:
   ```bash
   npm install -g pnpm@9.1.0
   ```

## Project Setup

1. Clone the repository:

   ```bash
   git clone git@github.com:hack-yeah-2024-fly/boilerplate.git
   cd node
   ```

2. Install dependencies:
   ```bash
   pnpm install
   ```

### Develop

To develop all apps and packages, run the following command:

```bash
pnpm dev
```

### Build

To build all apps and packages, run the following command:

```bash
pnpm build
```
