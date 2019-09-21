using PortfolioWeb.Classes;
using System;
using System.Web.Mvc;

namespace PortfolioWeb.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Information(string id)
        {
            string path = Server.MapPath($"~/App_Data/{id}/metadata.xml");

            if (!System.IO.File.Exists(path))
            {
                return View(new ProfileInfo(null, null));
            }

            ProfileInfo profile = new ProfileInfo(id, path);
            try
            {

                // Github, Linkedin Image 추가
                ViewBag.ID = id;
                ViewBag.ProfileImage = ImageUtil.ToBase64String(Server.MapPath($"~/App_Data/{id}/profile.jpg"));
                ViewBag.GithubImage  = ImageUtil.ToBase64String(Server.MapPath($"~/Resources/Logo/github.png"));
                ViewBag.LinkedinImage = ImageUtil.ToBase64String(Server.MapPath($"~/Resources/Logo/linkedin.png"));

            }
            catch (Exception)
            {
                ViewBag.ProfileImage = string.Empty;
                ViewBag.GithubImage = string.Empty;
                ViewBag.LinkedinImage = string.Empty;
            }


            return View(profile);
        }

        public ActionResult Detail(string name,string projectName)
        {

            String route = "~/App_Data/" + name + "/" + projectName;
            String picture = Server.MapPath(route);
            System.IO.DirectoryInfo di = new System.IO.DirectoryInfo(picture);
            int filelength = di.GetFiles().Length - 1;
            ViewBag.Route = picture;
            ViewBag.Picture = filelength;
            return View();

        }
    }
}

public class RequireHttpsConnectionFilter : RequireHttpsAttribute
{	    public override void OnAuthorization(AuthorizationContext filterContext)
	{
	    if (filterContext == null)
	    {
	        throw new ArgumentNullException("filterContext");
	    }
	 
	    if (filterContext.HttpContext.Request.IsLocal)
	    {
        //Local환경에서는 HTTPS를 사용하지 않도록 구성
        return;
	    }
	 
	    base.OnAuthorization(filterContext);
	}
}


