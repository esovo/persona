FROM node:18.12.1-alpine
WORKDIR /app_f
COPY package*.json ./

# Remove existing node_modules and package-lock.json
RUN rm -rf node_modules && rm -f package-lock.json

RUN npm install --force
COPY . .
RUN npm run build
EXPOSE 3000
CMD [ "npm", "start" ]
