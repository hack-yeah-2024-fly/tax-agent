# Use Node.js as the base image
FROM node:20-alpine AS base

# Install pnpm
RUN npm install -g pnpm@9.1.0

# Set working directory
WORKDIR /app

# Copy package.json files and workspace configuration
COPY package.json pnpm-workspace.yaml ./
COPY apps/browser-app/package.json ./apps/browser-app/
COPY packages/eslint-config/package.json ./packages/eslint-config/
COPY packages/typescript-config/package.json ./packages/typescript-config/
COPY packages/ui/package.json ./packages/ui/

# Install dependencies
RUN pnpm install --no-frozen-lockfile

# Copy the rest of the application code
COPY . .

# Build the browser app
FROM base AS browser-app-builder
WORKDIR /app/apps/browser-app
RUN pnpm run build

# Create the final browser app image
FROM nginx:alpine AS browser-app
COPY --from=browser-app-builder /app/apps/browser-app/dist /usr/share/nginx/html
COPY apps/browser-app/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
