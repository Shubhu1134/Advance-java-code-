# NiraSon Repository Setup Guide

## Overview
This guide documents the process of cloning and setting up the NiraSon private GitHub repository as the technical head of the company.

**Repository:** `git@github.com:nirasonsweb-lang/NiraSon.git`  
**GitHub User:** `Shubhu1134`

---

## Prerequisites

- Git installed and configured
- GitHub account with SSH key set up
- Access to the company GitHub account (`nirasonsweb-lang`)

---

## Setup Steps

### 1. SSH Key Generation

Generate an SSH key for secure authentication:

```bash
ssh-keygen -t ed25519 -C "your_email@company.com"
```

Press Enter for default location. Optionally add a passphrase for security.

### 2. Add SSH Key to GitHub

Copy your public key:

```bash
cat ~/.ssh/id_ed25519.pub
```

On GitHub:
- Go to **Settings** → **SSH and GPG keys**
- Click **New SSH key**
- Paste the key and save

### 3. Verify SSH Connection

Test the connection:

```bash
ssh -T git@github.com
```

Expected output: `Hi Shubhu1134! You've successfully authenticated...`

### 4. Get Repository Access

Ask the repository owner/admin to:
1. Go to the repo → **Settings** → **Collaborators**
2. Add your GitHub username (`Shubhu1134`) with appropriate access level

Once added, you'll receive a notification.

### 5. Clone the Repository

```bash
git clone git@github.com:nirasonsweb-lang/NiraSon.git
cd NiraSon
```

**Size:** ~476.78 MiB (2094 objects)  
**Time:** Approximately 2-3 minutes depending on connection speed

### 6. Install Dependencies

```bash
npm install
```

This installs 216+ packages. You may see some vulnerability warnings.

### 7. Fix Security Vulnerabilities

Address npm audit issues:

```bash
npm audit fix
```

If needed, force fix breaking changes:

```bash
npm audit fix --force
```

Update baseline-browser-mapping (optional):

```bash
npm i baseline-browser-mapping@latest -D
```

### 8. Start Development Server

```bash
npm run dev
```

The app will be available at:
- **Local:** `http://localhost:3000/`
- **Network:** `http://192.168.31.203:3000/`

---

## Project Structure

```
NiraSon/
├── src/                          # Main source code
├── public/                        # Static assets
├── dist/                          # Build output
├── tests/                         # Test files
├── package.json                   # Dependencies & scripts
├── vite.config.js               # Vite configuration
├── tailwind.config.js            # Tailwind CSS config
├── tsconfig.json                 # TypeScript configuration
├── playwright.config.ts          # E2E testing config
├── netlify.toml                  # Netlify deployment config
├── vercel.json                   # Vercel deployment config
├── README.md                      # Project documentation
└── PRODUCT_ADDITION_GUIDE.md     # Feature addition guide
```

---

## Tech Stack

- **Framework:** React/Vite
- **Language:** TypeScript
- **Styling:** Tailwind CSS
- **Testing:** Playwright
- **Backend:** Supabase
- **Deployment:** Netlify/Vercel

---

## Recent Features & Commits

### Latest Features
- Order tracking functionality
- Admin dashboard
- Order confirmation modal
- Supabase integration with error handling
- Cart and order improvements
- UI enhancements

### Key Commits
```
HEAD -> main
├── privacy,shipping,returns,terms updated
├── replace Add button in seeds section to 'Coming soon'
├── db policy add
├── Removed the unused FiCheckCircle
├── fix db error and fix mrp issue
├── ui improve, fix cart and order issue
├── Add Supabase error handling for unconfigured environments
└── Fix production issues: Add NODE_ENV to vite config
```

---

## Git Workflow

### View Commit History
```bash
git log --oneline -20
```

### Check Branches
```bash
git branch -a
```

### Create Feature Branch
```bash
git checkout -b feature/your-feature-name
```

### Switch to Main
```bash
git checkout main
```

### Commit Changes
```bash
git add .
git commit -m "description of changes"
git push origin feature/your-feature-name
```

### Create Pull Request
After pushing, create a PR on GitHub for code review.

---

## Common Commands

| Command | Purpose |
|---------|---------|
| `npm run dev` | Start development server |
| `npm run build` | Build for production |
| `npm test` | Run tests |
| `npm audit fix` | Fix security vulnerabilities |
| `git log --oneline -20` | View recent commits |
| `git branch -a` | List all branches |
| `git checkout -b feature/name` | Create new feature branch |
| `git push origin feature/name` | Push branch to remote |

---

## Important Notes

1. **Always pull latest before starting work:**
   ```bash
   git pull origin main
   ```

2. **Commit the updated `package-lock.json` after npm updates:**
   ```bash
   git add package-lock.json
   git commit -m "fix: update dependencies and resolve vulnerabilities"
   git push origin main
   ```

3. **Test locally before pushing:**
   ```bash
   npm run dev
   ```

4. **Use meaningful commit messages** following the team's convention

5. **Create feature branches** for all new work (don't commit directly to main)

---

## Deployment

The project is deployed on:
- **Netlify** (netlify.toml)
- **Vercel** (vercel.json)

Check these config files for deployment settings.

---

## Next Steps as Technical Head

1. ✅ Clone and set up the repository
2. ✅ Understand the current codebase
3. ✅ Review recent features and PRs
4. → Plan technical improvements
5. → Establish coding standards
6. → Set up CI/CD if needed
7. → Mentor team members on architecture

---

## Troubleshooting

### "Repository not found" Error
- Ensure you have SSH key added to GitHub
- Verify access via: `ssh -T git@github.com`
- Ask admin to add you as collaborator

### Dependencies Installation Issues
```bash
rm -rf node_modules package-lock.json
npm install
```

### Port 3000 Already in Use
```bash
npm run dev -- --port 3001
```

---

## References

- GitHub Docs: https://docs.github.com/
- Vite Docs: https://vitejs.dev/
- React Docs: https://react.dev/
- Tailwind CSS: https://tailwindcss.com/
- Supabase: https://supabase.com/docs

---

**Last Updated:** January 6, 2026  
**Created by:** Setup Documentation for NiraSon Technical Head
