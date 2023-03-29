/** @type {import('next').NextConfig} */

const path = require('path');

const nextConfig = {
  reactStrictMode: true,
  sassOption: {
    includePaths: [path.join(__dirname, 'styles')],
  },
};

module.exports = nextConfig;
