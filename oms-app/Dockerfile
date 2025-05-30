# Build stage
FROM node:18-alpine as builder

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Run stage
FROM node:18-alpine
WORKDIR /app

# Copy .output (build của Nuxt 3)
COPY --from=builder /app/.output ./

EXPOSE 3000
CMD ["node", "server/index.mjs"]
