const API_BASE = '/api';

export const api = {
  get: (url) => fetch(`${API_BASE}${url}`, { method: 'GET' }).then(r => r.json()),
  post: (url, body) => fetch(`${API_BASE}${url}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(body)
  }).then(r => r.json()),
};
