const $=s=>document.querySelector(s);const $$=s=>document.querySelectorAll(s);const esc=s=>String(s??'').replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('"','&quot;').replaceAll("'",'&#039;');
async function api(url,opt={}){const r=await fetch(url,{headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8',...(opt.headers||{})},...opt});let d={};try{d=await r.json()}catch{}if(!r.ok)throw new Error(d.error||'Request failed');return d}
async function session(){try{return await api('api/auth/me',{headers:{},method:'GET'})}catch{return {authenticated:false}}}
async function nav(){const n=await session();const el=$('#navUser');if(!el)return;if(n.authenticated)el.innerHTML=`<span>${esc(n.name)}</span><a class="btn ghost" href="${n.role==='ADMIN'?'admin.html':'dashboard.html'}">${n.role==='ADMIN'?'Admin Console':'My Dashboard'}</a><button class="btn" onclick="logout()">Logout</button>`;else el.innerHTML='<a href="login.html">Log in</a><a class="btn primary" href="register.html">Get started</a>'}
async function logout(){await api('api/auth/logout',{method:'POST',body:''});location.href='index.html'}
function toast(msg){const t=$('#toast');if(!t)return;t.textContent=msg;t.style.display='block';setTimeout(()=>t.style.display='none',2600)}
nav();
