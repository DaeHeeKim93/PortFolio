using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Web;

namespace PortfolioWeb.Classes
{
    public class ImageUtil
    {
        public static string ToBase64String(string path)
        {
            using (Image image = Image.FromFile(path))
            {
                using (MemoryStream ms = new MemoryStream())
                {
                    image.Save(ms, image.RawFormat);
                    byte[] buff = ms.ToArray();

                    return Convert.ToBase64String(buff);
                }
            }
        }
    }
}