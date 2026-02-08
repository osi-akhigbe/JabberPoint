# Push This Project to a New GitHub Repository

Follow these steps to create a new GitHub repo for your group and push this project to it.

---

## 1. Create the new repository on GitHub

1. Go to **https://github.com/new**
2. Sign in if needed.
3. Set:
   - **Repository name:** e.g. `Jabberpoint` or `jabberpoint-group`
   - **Description:** (optional) e.g. "JabberPoint presentation tool - group project"
   - **Visibility:** Public or Private (as required for your group)
   - **Do not** check "Add a README", "Add .gitignore", or "Choose a license" (this folder already has content).
4. Click **Create repository**.

---

## 2. Push this project from your machine

Open a terminal, go to this project folder, then run:

```bash
cd /Users/osi/Desktop/Jabberpoint

# Initialize Git (if not already done)
git init

# Ignore build and system files (already added via .gitignore)
# Stage everything
git add .
git status

# First commit
git commit -m "Initial commit: JabberPoint project with docs and diagrams"

# Rename branch to main if needed
git branch -M main

# Add your new GitHub repo as remote (replace with your actual repo URL)
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git

# Push to GitHub
git push -u origin main
```

Replace `YOUR_USERNAME` and `YOUR_REPO_NAME` with your GitHub username and the repository name you chose (e.g. `https://github.com/osi/jabberpoint-group.git`).

---

## 3. If you use SSH instead of HTTPS

```bash
git remote add origin git@github.com:YOUR_USERNAME/YOUR_REPO_NAME.git
git push -u origin main
```

---

## 4. Invite your group (if the repo is under your account)

1. Open the repo on GitHub → **Settings** → **Collaborators** (or **Manage access**).
2. Click **Add people** and add your teammates by GitHub username or email.

---

## Quick one-liner (after creating the repo on GitHub)

After you’ve created the empty repo on GitHub, run this from `/Users/osi/Desktop/Jabberpoint` (replace the URL):

```bash
cd /Users/osi/Desktop/Jabberpoint && git init && git add . && git commit -m "Initial commit: JabberPoint project" && git branch -M main && git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git && git push -u origin main
```

Then invite your group from the repo’s **Settings → Collaborators**.
