using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using BootCamp.Models;
using Microsoft.AspNetCore.Hosting;

// 시작 화면 및 XML 파싱하여 보여주는 Controller
namespace BootCamp.Controllers
{
    public class HomeController : Controller
    {
        /* WEBROOT 경로를 위한 호스팅 작업 */
        /* Metadata.XML 경로를 위한 작업 */
        private readonly IHostingEnvironment _hostingEnvironment;

        public HomeController(IHostingEnvironment hostingEnvironment)
        {
            _hostingEnvironment = hostingEnvironment;
        }

        // 시작 페이지
        public IActionResult Index()
        {
            return View();
        }

        // 개인 프로필 가져오기  
        public IActionResult Information(string id)
        {

            // MetaData를 가져오기 위한 webroot 가져오는 변수 
            string webRootPath = _hostingEnvironment.WebRootPath;
            // MetaData File 자체를 가지고 옴
            string path = webRootPath + "/" + id + "/metadata.xml";


            if (!System.IO.File.Exists(path))
            {
                return View(new ProfileInfo(null, null));
            }
           
            ProfileInfo profile = new ProfileInfo(id, path);
            try
            {
                // Profile, Festa, Azure 
                String ProfileImageRoute = "/Logo/profile.png";
                String FestaImageRoute = "/Logo/Festa.png";
                String AzureImageRoute = "/Logo/AzureBootCamp.png";

                // Festa, AzureBootCamp
                ViewBag.ID = id;
                ViewBag.ProfileImage = ProfileImageRoute;
                ViewBag.FestaImage = FestaImageRoute;
                ViewBag.AzureBootCampImage = AzureImageRoute;

            }
            catch (Exception)
            {
                ViewBag.ProfileImage = string.Empty;
                ViewBag.FestaImage = string.Empty;
                ViewBag.AzureBootCampImage = string.Empty;
            }


            return View(profile);
        }

        
    }
}

