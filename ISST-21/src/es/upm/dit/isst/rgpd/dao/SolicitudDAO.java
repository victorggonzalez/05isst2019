
public interface SolicitudDAO {
	public void create(Solicitud solicitud);
	public Solicitud read(String email);
	public void update(Solicitud solicitud);
	public void delete(Solicitud solicitud);
	public Collection<Solicitud> readAll();
}
