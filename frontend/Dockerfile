FROM node:23-alpine AS build-stage
#FROM arm32v7/node:23-alpine AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm audit fix && npm install
COPY . .
RUN npm run build
EXPOSE 80
CMD ["npm", "run", "serve"]

# OBS! Bygg och pusha båda Docker-images till Docker Hub med;
# docker buildx build --platform linux/arm64/v8 -t mattan41/frontendcinemascalaforrpi:latest --push ./frontend
# testade men hänger vid även med npm install  med arm32v7/node:23-alpine och bygg och pusha till Docker Hub med;
# docker buildx build --platform linux/arm/v7 -t mattan41/frontendcinemascalaforrpi:latest --push ./frontend