using MO.Common.Net.Sockets;

namespace MO.Core.Logic.Client {

    public interface IInfoClient {

       FSocket Socket { get; }

       void Connect(string host, int port);

       void Disconnect();
    
    }

}
