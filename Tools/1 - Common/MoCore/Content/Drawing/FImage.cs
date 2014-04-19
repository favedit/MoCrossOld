using System.Drawing;

namespace MO.Core.Content.Drawing
{
    public class FImage {

        private Image _image;

        public FImage() {
        }

        public FImage(string file) {
            LoadFile(file);
        }

       public FImage(int width, int height) {
           _image = new Bitmap(width, height);
        }

        public Image Image {
            get { return _image; }
            set { _image = value; }
        }

        public void Create(int width, int height) {
            _image = new Bitmap(width, height);
        }

        public void LoadFile(string file) {
            _image = Image.FromFile(file);
        }

    }
}
