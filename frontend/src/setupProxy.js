const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    createProxyMiddleware(['/api', '/hello'], {
      target: "http://localhost:8081",
      changeOrigin: true,
    })
  );
};