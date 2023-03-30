import React from 'react';
import { Dashboard } from './components/Dashboard';
import * as ReactDOM from 'react-dom/client';
// import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './index.css';
import { DashboardContextProvider } from './components/Dashboard';
import { SettingsContextProvider } from './components/Settings';
import Main  from './Pages/Main/Main';

function App() {
  return (
    <div className="App">
      <header className="App-header">
      <DashboardContextProvider>
        <SettingsContextProvider>
          <Routes>
          <Route path='/' element={
            <Main></Main>
            } />
        {/* <Route path='/Bookmark' element={<Bookmark/>} />
          <Route path='/Community' element={<Community/>} />
          <Route path='/Mypage' element={<Mypage/>} />
          <Route path='/Practice' element={<Practice/>} />
          <Route path='/Storage' element={<Storage/>} /> */}
            <Route path='/dashboard' element={
              <React.Suspense fallback={<>Loading Fallback ...</>}>
                <Dashboard />
              </React.Suspense>
            } />
          </Routes>
        </SettingsContextProvider>
      </DashboardContextProvider>
      </header>
    </div>
  );
}

export default App;


