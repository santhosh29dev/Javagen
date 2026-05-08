#!/usr/bin/env bash
# Install javagen from GitHub Releases
# Usage: curl -fsSL https://github.com/santhosh29dev/Javagen/releases/latest/download/install.sh | bash

set -e

REPO="santhosh29dev/Javagen"
INSTALL_DIR="$HOME/.local/bin"
JAR_NAME="javagen.jar"

echo "Installing javagen..."

# Get latest release download URL
LATEST_URL=$(curl -fsSL "https://api.github.com/repos/${REPO}/releases/latest" | grep "browser_download_url.*\.jar" | head -1 | cut -d'"' -f4)

if [ -z "$LATEST_URL" ]; then
    echo "ERROR: Could not find latest release JAR."
    exit 1
fi

mkdir -p "$INSTALL_DIR"

echo "Downloading javagen.jar..."
curl -fsSL -o "${INSTALL_DIR}/${JAR_NAME}" "$LATEST_URL"

# Create wrapper script
cat > "${INSTALL_DIR}/javagen" << 'EOF'
#!/usr/bin/env bash
java -jar "$HOME/.local/bin/javagen.jar" "$@"
EOF
chmod +x "${INSTALL_DIR}/javagen"

echo ""
echo "javagen installed to ${INSTALL_DIR}/javagen"
echo ""
echo "Make sure ${INSTALL_DIR} is in your PATH:"
echo "  export PATH=\"\$HOME/.local/bin:\$PATH\""
echo ""
echo "Or for your profile (~/.bashrc, ~/.zshrc):"
echo "  echo 'export PATH=\"\$HOME/.local/bin:\$PATH\"' >> ~/.bashrc"
echo ""
echo "Then run: javagen create web --name myproject"
